#include <iostream>
#include <vector>
using namespace std;

int contarPares(vector <int> v, int cont)
{
	for (int i = 0; i < v.size(); i++)
	{
		if (v[i] % 2 == 0)
		{
			cont++;
		}
	}

	return cont;
}

int main()
{
	int casos;
	vector <int> v;
	int numerosLoteria;
	int decimos;
	int i = 0;

	cin >> casos;
	
	while (i < casos)
	{
		cin >> decimos;

		for (int i = 0; i < decimos; i++)
		{
			cin >> numerosLoteria;
			v.push_back(numerosLoteria);
		}

		int cont = 0;
		int contador = contarPares(v, cont);

		cout << contador << endl;

		for (int i = 0; i < decimos; i++)
		{
			v.pop_back();
		}

		i++;
	}
	
	return 0;
}