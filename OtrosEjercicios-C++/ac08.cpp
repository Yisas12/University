#include <iostream>
#include <vector>
using namespace std;

int resolver(const vector<int> &v, int tam)
{
	int max = v[0];
	int cont = 1, contMax = 1;

	for (int i = 1; i < tam; i++)
	{
		if (max < v[i])
		{
			max = v[i];
			if (contMax < cont) contMax = cont;
			cont = 1;
		}
		else
		{
			max = v[i];
			cont++;
		}

		if (contMax < cont) contMax = cont;
	}

	return contMax;
}

void resuelveCaso()
{
	int tamVector, numeros, p;
	vector<int> v;

	cin >> tamVector;

	if (tamVector == 0) p = 0;
	else {
		for (int i = 0; i < tamVector; i++)
		{
			cin >> numeros;
			v.push_back(numeros);
		}

		p = resolver(v, tamVector);
	}
	

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