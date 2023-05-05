from functools import cache


@cache
def g(R, D, m, n):
    # sourcery skip: assign-if-exp, hoist-repeated-if-condition, reintroduce-else
    if D == 0 and R >= m[0] and R <= n[0]:
        return 1

    if D == 0:
        return 0

    return sum(g(R - x, D - 1, m, n) for x in range(m[D], n[D] + 1))


print(g(10, 2, (4, 1, 1), (7, 2, 3)))
