#include <iostream>
#include <vector>
using namespace std;

int resolver(const vector<int> &v)
{
	int max = v[0];
	int cont = 0, aux = 0;

	for (int i = 1; i < v.size(); i++)
	{
		if (max < v[i]) {
			max = v[i];
			if (cont < aux) cont = aux;
			aux = 0;
		}
		else
		{
			aux++;
			if (i == v.size() - 1 && cont < aux) cont = aux;
		}

		//if (max > v[v.size() - 1] && i == v.size() - 1) cont = aux;
	}

	return cont;
}

void resuelveCaso()
{
	int numeros, numeroVector, p;
	vector<int> v;

	cin >> numeroVector;

	for (int i = 0; i < numeroVector; i++)
	{
		cin >> numeros;
		v.push_back(numeros);
	}

	p = resolver(v);

	cout << p << "\n";
}

int main()
{
	int casos;

	cin >> casos;

	for (int i = 0; i < casos; i++)
	{
		resuelveCaso();
	}

	//system("pause");
	return 0;
}