#include <iostream>
#include <vector>
using namespace std;

long long int sumaFuncion(long long int casos, long long int &suma, vector<long long int> v)
{
	long long int div = 0, mod = 0;
	long long int p;

	mod = casos % 10;
	
	if (casos / 10 == 0)
	{
		v.push_back(mod);
		suma += mod;

		for (int i = v.size() - 1; i >= 0; i--)
		{
			if (i != 0) cout << v[i] << " + ";
			else cout << v[i] << " = ";

		}
	}
	else
	{
		v.push_back(mod);
		suma += mod;

		p = sumaFuncion(casos / 10, suma, v);
	}

	return suma;
}

int main()
{
	long long int casos, suma = 0;
	int p;
	vector<long long int> h;

	cin >> casos;

	while (casos >= 0)
	{
		p = sumaFuncion(casos, suma, h);
		cout << p << endl;
		suma = 0;
		cin >> casos;
	}

	//system("pause");
	return 0;
}