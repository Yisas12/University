#include <iostream>
#include <vector>
using namespace std;

int resolver(const vector<int> &v, int x, int ini, int fin)
{
	//caso base

	if (fin - ini == 0) //1 elemento
	{
		return v[ini];
	}
	
	if (fin - ini == 1) //2 elementos
	{
		if (abs(v[ini] - x) < abs(v[fin] - x)) return v[ini];
		else if (abs(v[ini] - x) > abs(v[fin] - x)) return v[fin];
		else {
			if (v[ini] < v[fin]) return v[ini];
			else return v[fin];
		}
	}

	//caso recursivo

	int mitad = (ini + fin) / 2;
	int izq, der;

	izq = resolver(v, x, ini, mitad);
	der = resolver(v, x, mitad + 1, fin);

	if (abs(izq - x) < abs(der - x)) return izq;
	else if (abs(izq - x) > abs(der - x)) return der;
	else {
		if (izq < der) return izq;
		else return der;
	}
}

void resuelveCaso()
{
	int x, tam, nums, p;
	vector<int> v;

	cin >> x >> tam;

	for (int i = 0; i < tam; i++)
	{
		cin >> nums;
		v.push_back(nums);
	}

	p = resolver(v, x, 0, v.size() - 1);

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