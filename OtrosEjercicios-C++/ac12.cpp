#include <iostream>
#include <vector>
using namespace std;

int resolver(const vector<int> &v, int ini, int fin)
{
	int mitad = (fin + ini) / 2;
	//int aux = 0;
	int izq, der;
	
	if (fin - ini == 0) return v[ini];

	if (fin - ini == 1)
	{
		if (v[ini] < v[fin]) return v[ini];
		else return v[fin];
	}
	else
	{
		izq = resolver(v, ini, mitad);
		der = resolver(v, mitad + 1, fin);

		if (izq < der) return izq;
		else return der;
	}
}

bool resuelveCaso()
{
	int tam, nums, p;
	vector<int> v;

	cin >> tam;

	if (!cin) return false;
	
	for (int i = 0; i < tam; i++)
	{
		cin >> nums;
		v.push_back(nums);
	}

	p = resolver(v, 0, tam - 1);

	cout << p << "\n";

	return true;
}

int main()
{
	while (resuelveCaso());

	system("pause");
	return 0;
}