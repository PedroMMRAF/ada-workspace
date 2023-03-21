import random


EMPTY = "e"
OBJECTS = ["h", "p", "c"]
MONSTERS = ["3", "t", "d"]


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


def intput(prompt: str) -> int:
    while not (res := input(prompt)).isdigit():
        pass
    return int(res)


def main():
    count = intput("Count: ")
    size = intput("Size: ")

    for _ in range(count):
        print(generate(size))


if __name__ == "__main__":
    main()
