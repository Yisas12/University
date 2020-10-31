#include <iostream>
#include <vector>
using namespace std;

int resolver(const vector<int> &v, int ini, int fin)
{
	if (fin - ini == 1)
	{
		if (v[ini] < v[fin]) return v[ini] + v[fin];
		else return -1;
	}

	int mitad = (ini + fin) / 2;
	int izq, der;
	izq = resolver(v, ini, mitad);
	der = resolver(v, mitad + 1, fin);

	if (izq == -1 || der == -1) return -1;

	if (izq < der) return izq + der;
	else return -1;
}

bool degradado(const vector<int> &v) {
	if (v.size() < 2)
		return true;
	return resolver(v, 0, v.size() - 1) > 0; //si lo que devuelve es > 0 true, sino false
}

bool resuelveCaso()
{
	int filas, columnas, nums;
	vector<int> v;
	bool b = true;

	cin >> filas >> columnas;

	if (!cin) return false;

	for (int i = 0; i < filas; i++)
	{
		for (int j = 0; j < columnas; j++)
		{
			cin >> nums;
			v.push_back(nums);
		}

		if (b) {
			b = degradado(v);
		}

		v.clear();
	}

	if (b) cout << "SI\n";
	else cout << "NO\n";

	return true;
}

int main()
{
	while (resuelveCaso());

	//system("pause");
	return 0;
}