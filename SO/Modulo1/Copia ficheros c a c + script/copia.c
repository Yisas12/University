//Jesus Cencerrado Pedrero
#include <stdio.h>

int main()
{
FILE *entrada, *salida;
int num = 0;

entrada = fopen("entrada.txt", "r");
salida = fopen("salida.txt", "w");

while((num = getc(entrada)) != EOF)
{
    fputc(num, salida);
}

fclose(entrada);
fclose(salida);

return 0;
}
