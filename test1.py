import sys
import logging

log = logging.getLogger()


EMPTY = "e"
OBJECTS = ["h", "p", "c"]
MONSTERS = ["3", "t", "d"]


def solve(path: str) -> int:
    n = len(path)
    dp = [[sys.maxsize for _ in range(3)] for _ in range(n + 1)]
    dp[0][0] = 0

    for i in range(1, n + 1):
        tile = path[i - 1]

        # check if we can defeat any monsters with an object
        for j in range(3):
            if tile in MONSTERS and MONSTERS.index(tile) > j:
                continue

            if tile in OBJECTS and OBJECTS.index(tile) == j:
                for k in range(j + 1, 3):
                    dp[i][k] = min(dp[i][k], dp[i - 1][j] + 2)

        # check if we can defeat any monsters without an object
        for j in range(3):
            if tile in MONSTERS and MONSTERS.index(tile) > j:
                continue

            for k in range(j, 3):
                dp[i][j] = min(dp[i][j], dp[i - 1][k] + 5 + j - k)

        # update the state without picking up any objects
        for j in range(3):
            if tile in MONSTERS and MONSTERS.index(tile) > j:
                continue

            dp[i][j] = min(dp[i][j], dp[i - 1][j] + 1)

    # return the minimum cost to reach the end without holding any objects
    return min(dp[n][j] for j in range(3))


def main():
    logging.basicConfig(level=logging.INFO)

    log.info("Result: %d", solve("peecttdhp3e"))
    log.info("Result: %d", solve("ecedeeeeeedee"))


if __name__ == "__main__":
    main()
