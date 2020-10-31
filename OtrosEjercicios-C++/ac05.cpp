#include <iostream>
#include <vector>
using namespace std;

bool creciente(const vector<int> &v)
{
	int i = 0;

	while ((i < v.size() - 2) && (v[i] <= v[i + 1]) && ((v[i + 1] - v[i] == 1) || (v[i + 1] - v[i] == 0)))
	{
		i++;
	}

	if (i == v.size() - 2) return true;
	else return false;
}

bool diver(const vector<int> &v, int num) 
{
	int cont = 1;

	for (int i = 0; i < v.size(); i++)
	{
		for (int j = i + 1; j < v.size(); j++)
		{
			if (v[i] == v[j]) cont++;
		}

		if (cont > num) return false;
		cont = 1;
	}

	return true;
}

void resuelveCaso()
{
	bool divertido = false;
	bool crec = false;
	vector<int> v;
	int numVector, sep, numeros;

	cin >> sep >> numVector;

	for (int i = 0; i < numVector; i++)
	{
		cin >> numeros;
		v.push_back(numeros);
	}

	divertido = diver(v, sep);
	crec = creciente(v);

	if (divertido && crec) cout << "SI" << "\n";
	else cout << "NO" << "\n";
}

int main()
{
	int numCasos;

	cin >> numCasos;

	for (int i = 0; i < numCasos; i++)
	{
		resuelveCaso();
	}

	//system("pause");
	return 0;
}