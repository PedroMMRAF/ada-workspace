import random
from io import TextIOWrapper
from dataclasses import dataclass


@dataclass(frozen=True)
class City:
    pref: str
    value: int

    def __eq__(self, other: "City") -> bool:
        return self.pref == other.pref


@dataclass
class Solver:
    CACHE = {}

    north: list[City]
    south: list[City]

    def recursive(self) -> tuple[int, int]:
        return self.L(len(self.north) - 1, len(self.south) - 1)

    def L(self, n: int, s: int) -> tuple[int, int]:
        if maybe := self.CACHE.get((n, s)):
            return maybe

        if n == -1 or s == -1:
            return 0, 0

        value = 0
        bridge = 0

        v1, b1 = self.L(n - 1, s)
        v2, b2 = self.L(n, s - 1)

        if v1 > v2:
            value = v1
            bridge = b1
        elif v2 > v1:
            value = v2
            bridge = b2
        else:
            value = v1
            bridge = min(b1, b2)

        if self.north[n] == self.south[s]:
            v3, b3 = self.L(n - 1, s - 1)
            v3 += self.north[n].value + self.south[s].value
            b3 += 1

            if v3 > value or (v3 == value and b3 < min(b1, b2)):
                value = v3
                bridge = b3

        self.CACHE[n, s] = value, bridge

        return value, bridge


def generate(north: int, south: int, types: list, values: range):
    types = list(types)

    yield "1\n"

    yield f"{north}\n"
    for _ in range(north):
        yield f"_ {random.choice(types)} {random.randrange(values.start, values.stop, values.step)}\n"

    yield f"{south}\n"
    for _ in range(south):
        yield f"_ {random.choice(types)} {random.randrange(values.start, values.stop, values.step)}\n"


def solve_from(reader: TextIOWrapper):
    samples: list[Solver] = []

    sampleN = int(reader.readline())

    for _ in range(sampleN):
        cityN = int(reader.readline())

        north = []
        for _ in range(cityN):
            _, pref, value = reader.readline().split(" ")
            north.append(City(pref, int(value)))

        cityN = int(reader.readline())

        south = []
        for _ in range(cityN):
            _, pref, value = reader.readline().split(" ")
            south.append(City(pref, int(value)))

        samples.append(Solver(north, south))

    for sample in samples:
        print(sample.recursive())


def test() -> None:
    with open("test.txt", "r") as fp:
        solve_from(fp)


def main():
    with open("test.txt", "w") as fp:
        fp.writelines(generate(15, 15, range(4), range(10, 1000000, 1000)))


if __name__ == "__main__":
    test()
