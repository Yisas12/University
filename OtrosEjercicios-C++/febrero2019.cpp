#include <iostream>
#include <vector>
using namespace std;

const int N = 100;

int resolver(bool v[], int k, int tamV)
{
	int contUnosI = 0, auxIzq = 0, i = 0, contNums = 0, contUnosD = 0, j = tamV - 1, auxDer = 0;

	while (contUnosI < k && i < tamV)
	{
		if (v[i] == 1) {
			contUnosI++;
		}
		contNums++;
		auxIzq = contNums;
		i++;
	}

	contNums = 0;

	while (contUnosD < k && j >= 0)
	{
		if (v[j] == 1)
		{
			contUnosD++;
		}
		contNums++;
		auxDer = contNums;
		j--;
	}

	if (contUnosI != k) auxIzq++;
	if (contUnosD != k) auxDer++;

	if (auxDer < auxIzq) return auxDer;//cout << auxDer << "\n";
	else return auxIzq;//cout << auxIzq << "\n";
}

void resuelveCaso(int tamVector)
{
	int k, p;
	bool a[N];

	for (int i = 0; i < tamVector; i++)
	{
		cin >> a[i];
	}

	cin >> k;

	if (tamVector == 0)
	{
		p = 1;

		cout << p << "\n";
	}
	else {
		p = resolver(a, k, tamVector);

		cout << p << "\n";
	}
}

int main()
{
	int nums;

	cin >> nums;

	while (nums != -1)
	{
		resuelveCaso(nums);
		cin >> nums;
	}

	//system("pause");
	return 0;
}