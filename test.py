import sys
import logging

log = logging.getLogger()


EMPTY = "e"
OBJECTS = ["h", "p", "c"]
MONSTERS = ["3", "t", "d"]


def solve(path: str) -> int:
    return no_item(path, 0) - (path[-1] in MONSTERS)


def no_item(path: str, total: int) -> int:
    if not path:
        log.debug("reached start")
        return total

    tile = path[-1]

    if tile in MONSTERS:
        log.debug("found monster")
        return min(holding_item(path[:-1], total + 5 + i, i) for i in range(MONSTERS.index(tile), 3))

    log.debug("trivial")
    return no_item(path[:-1], total + 1)


def holding_item(path: str, total: int, obj: int) -> int:
    if not path:
        log.debug(f"{obj}: path impossible (reached start)")
        return sys.maxsize

    tile = path[-1]

    if tile in MONSTERS and MONSTERS.index(tile) > obj:
        log.debug(f"{obj}: path impossible")
        return sys.maxsize

    if tile in OBJECTS and OBJECTS.index(tile) == obj:
        log.debug(f"{obj}: found object")
        return no_item(path[:-1], total + 2)

    if tile in MONSTERS:
        log.debug(f"{obj}: found monster")
        return holding_item(path[:-1], total + 4 + obj, obj)

    log.debug(f"{obj}: found nothing")
    return holding_item(path[:-1], total + 3, obj)


def iterative():
    pass


def main():
    logging.basicConfig(level=logging.INFO)

    log.info("Result: %d", solve("peecttdhp3e"))
    log.info("Result: %d", solve("ecedeeeeeedee"))
    log.info("Result: %d", solve("ecedeeeeeedee"))


if __name__ == "__main__":
    main()
