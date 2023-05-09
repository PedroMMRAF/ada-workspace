#include <stdio.h>
#include <stdlib.h>

void flush()
{
    while (getc(stdin) != '\n')
        ;
}

void readline(char *out, int size)
{
    fgets(out, size + 1, stdin);
    flush();
}

int main()
{
    int rows, cols, cases;

    scanf("%d %d %d", &rows, &cols, &cases);
    flush();

    char **field = (char **)malloc(rows);

    for (int i = 0; i < rows; i++)
    {
        field[i] = (char *)malloc(cols);
        readline(field[i], cols);
    }

    printf("%d %d %d", rows, cols, cases);
    flush();

    for (int i = 0; i < rows; i++)
    {
        printf("%s\n", field[i]);
    }

    return 0;
}
