import math
import random
import logging
import time
from functools import cache
from dataclasses import dataclass, field
import sys

sys.setrecursionlimit(10000)


log = logging.getLogger()


EMPTY = "e"
OBJECTS = ["h", "p", "c"]
MONSTERS = ["3", "t", "d"]


def time_it(func):
    def wrapped(*args, **kwargs):
        time_result = time.perf_counter_ns()
        result = func(*args, **kwargs)
        time_result = time.perf_counter_ns() - time_result
        time_result /= 1e6
        log.debug(f"{func.__name__} took {time_result} ms")
        return result

    return wrapped


@time_it
def recursive(path: str):
    return _recursive(path, -1)


@cache
def _recursive(path: str, item: int) -> int:
    if not path:
        return 0

    tile = path[0]

    if item == -1:
        if tile == EMPTY:
            return 1 + _recursive(path[1:], -1)

        elif tile in OBJECTS:
            return min(
                2 + _recursive(path[1:], OBJECTS.index(tile)),
                1 + _recursive(path[1:], -1),
            )

        return math.inf

    if tile == EMPTY:
        return min(
            3 + _recursive(path[1:], item),
            2 + _recursive(path[1:], -1),
        )

    elif tile in OBJECTS:
        return min(
            3 + _recursive(path[1:], item),
            3 + _recursive(path[1:], OBJECTS.index(tile)),
            2 + _recursive(path[1:], -1),
        )

    if item < MONSTERS.index(tile):
        return math.inf

    return 4 + item + _recursive(path[1:], item)


@dataclass
class Frame:
    CACHE = {}

    path: str
    time: int
    item: int

    parent: "Frame" = field(default=None, repr=False)
    param: int = -1
    branches: list[int] = field(default_factory=list)
    children: list["Frame"] = field(default_factory=list, repr=False)

    def get_result(self):
        result = min(self.branches)
        self.CACHE[self.path, self.time, self.item] = result
        return result

    def pass_to_parent(self, result):
        self.parent.branches[self.param] = result

    def add_child(self, time, obj):
        data = (
            self.path[1:],
            self.time + time,
            obj,
        )

        if maybe := self.CACHE.get(data):
            self.branches.append(maybe)
            return

        self.children.append(
            Frame(
                *data,
                self,
                len(self.branches),
            )
        )
        self.branches.append(math.inf)

    def get_next_child(self):
        self.set_children()

        try:
            return self.children.pop(1)
        except IndexError:
            return

    def set_children(self):
        if self.children:
            return

        self.children.append(None)

        if not self.path:
            self.branches.append(self.time)
            return

        tile = self.path[0]

        if self.item == -1:
            if tile == EMPTY:
                self.add_child(1, -1)
                return

            if tile in OBJECTS:
                self.add_child(1, -1)
                self.add_child(2, OBJECTS.index(tile))
                return

            self.branches.append(math.inf)
            return

        if tile == EMPTY:
            self.add_child(3, self.item)
            self.add_child(2, -1)
            return

        if tile in OBJECTS:
            self.add_child(3, self.item)
            self.add_child(3, OBJECTS.index(tile))
            self.add_child(2, -1)
            return

        if self.item >= MONSTERS.index(tile):
            self.add_child(4 + self.item, self.item)
            return

        self.branches.append(math.inf)
        return


@time_it
def iterative(full_path: str):
    start = Frame(full_path, 0, -1)
    stack = [start]

    result = None

    while stack:
        frame = stack.pop()

        if child := frame.get_next_child():
            stack.append(frame)
            stack.append(child)

        else:
            result = frame.get_result()

        if frame.parent is not None:
            frame.pass_to_parent(result)

    return start.get_result()


def generate(size: int):
    result = ""
    valid = {EMPTY, *OBJECTS}

    for _ in range(size):
        tile = random.choice(list(valid))

        if tile in OBJECTS:
            idx = OBJECTS.index(tile)
            valid.update(MONSTERS[: idx + 1])

        result += tile

    return result


def generate_examples(lines, size, output=sys.stdout):
    for _ in range(lines):
        rand = generate(size)
        output.write(f"{rand} : {recursive(rand)}\n")


def main():
    logging.basicConfig(level=logging.INFO)

    print(recursive("peecttdhp3e"))

    # with open("test.txt", "w") as fp:
    #     generate_examples(10, 10, fp)
    #     generate_examples(10, 20, fp)
    #     generate_examples(10, 50, fp)
    #     generate_examples(10, 100, fp)
    #     generate_examples(10, 500, fp)
    #     generate_examples(5, 1000, fp)

    # generate_examples(1, 2500)
    # generate_examples(1, 10000)


if __name__ == "__main__":
    main()
