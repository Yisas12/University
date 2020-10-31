#include <iostream>
#include <vector>
using namespace std;

int resuelveCaso(int num)
{
	vector<int> v;
	int i = 1;
	int numero = 0;

	while (num != 0)
	{
		v.push_back(num % 10);
		num = num / 10;
		i *= 10;
	}

	i /= 10;

	for (int j = 0; j < v.size(); j++)
	{
		numero = numero + v[j] * i;
		i /= 10;
	}

	return numero;
}

int main()
{
	int numCasos;
	int p;

	cin >> numCasos;

	while(numCasos != 0)
	{
		p = resuelveCaso(numCasos);
		cout << p << "\n";

		cin >> numCasos;
	}

	//system("pause");
	return 0;
}