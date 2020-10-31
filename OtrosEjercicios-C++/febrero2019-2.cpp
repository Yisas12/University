#include<iostream>
using namespace std;

bool resolver(int num, int sum, int cont)
{
	int numAux = num;
	

	//caso base
	if (num == 0)
	{
		return true;
	}

	//caso recursivo
	if (sum % cont == 0) //divisible
	{
		cont = 1;
		sum = 0;

		while (numAux / 10 > 0)
		{
			cont++;
			sum += numAux % 10;
			numAux /= 10;
		}

		//por la ultima vuelta
		sum += numAux % 10;
		//cont++;

		return resolver(num / 10, sum, cont);
	}
	else
	{
		return false;
	}
}

bool resuelveCaso()
{
	int num;
	bool b;
	int suma = 0;
	int cont = 1;

	cin >> num;

	if (num == 0) return false;

	b = resolver(num, suma, cont);

	if (b) cout << "SI" << "\n";
	else cout << "NO" << "\n";

	return true;
}

int main()
{
	while (resuelveCaso());

	//system("pause");
	return 0;
}