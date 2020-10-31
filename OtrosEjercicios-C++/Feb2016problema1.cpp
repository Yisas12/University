#include <iostream>
#include <vector>
using namespace std;

//Número de pares a la izq por cada impar
int parImpar(vector<int> v)
{
	int acumTotal = 0, acumP = 0;

	for (int i = 0; i < v.size(); i++)
	{
		if ((v[i]&1) == 0) acumP++; //v[i]&1 == v[i]%2
		else acumTotal += acumP;
	}

	return acumTotal;
}

int main()
{
	int numerosArray, casos;
	vector <int> v;

	cin >> casos;
	
	for (int i = 0; i < casos; i++)
	{
		cin >> numerosArray;
		v.push_back(numerosArray);
	}

	int p = parImpar(v);
	cout << p << endl;

	for (int i = 0; i < v.size(); i++)
	{
		v.pop_back();
	}
	
	system("pause");
	return 0;
}

