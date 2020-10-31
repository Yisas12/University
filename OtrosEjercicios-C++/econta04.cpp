#include<iostream>
#include<vector>
using namespace std;

int resolver(const vector<int> &v, int ini, int fin)
{
	//casos base

	if (fin - ini == 0) //1 elemento
	{
		return v[ini];
	}

	if (fin - ini == 1) //2 elementos
	{
		if (v[ini] % 2 == 1) return v[ini];
		else return v[fin];
	}

	//caso recursivo

	int mitad = (ini + fin) / 2;
	int izq, der;

	izq = resolver(v, ini, mitad);

	if (izq % 2 == 1) return izq;
	else {
		der = resolver(v, mitad + 1, fin);
		return der;
	}
}

bool resuelveCaso()
{
	int tam, nums, p;
	vector<int> v;

	cin >> tam;

	if (tam == 0) return false;

	for (int i = 0; i < tam; i++)
	{
		cin >> nums;
		v.push_back(nums);
	}

	p = resolver(v, 0, v.size() - 1);

	cout << p << "\n";

	return true;
}

int main()
{
	while (resuelveCaso());

	//system("pause");
	return 0;
}