#include <iostream>
using namespace std;

void saltos()
{

	int numSaltos, arriba = 0, abajo = 0, i = 0;
	long long int numero, aux;

	cin >> numSaltos;

	cin >> numero;
	aux = numero;

	while (i < numSaltos - 1)
	{
		cin >> numero;
		
		if (aux > numero) abajo++;
		else if (aux < numero) arriba++;
		else
		{
			abajo = abajo;
			arriba = arriba;
		}

		aux = numero;
		i++;
	}

	cout << arriba << " " << abajo << endl;

	abajo = 0;
	arriba = 0;
}

int main()
{
	int casos;
	cin >> casos;

	for (int i = 0; i < casos; i++)
	{
		saltos();
	}

	//system("pause");
	return 0;
}