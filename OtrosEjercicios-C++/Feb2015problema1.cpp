#include <iostream>
#include <vector>
using namespace std;

//Precondicion: n >= 0
//Postcondicion: 

int sumProd(vector <int> v)
{
	int suma = 0;
	int i = 1;
	int acum = v[0];

	for (i; i < v.size(); i++)
	{
		suma += v[i] * acum;
		acum += v[i];
	}

	return suma;
}

int main()
{
	int numerosArray, n, res;
	vector <int> v;

	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> numerosArray;
		v.push_back(numerosArray);
	}

	res = sumProd(v);

	cout << res << endl;

	for (int i = 0; i < v.size(); i++)
	{
		v.pop_back();
	}

	system("pause");
	return 0;
}