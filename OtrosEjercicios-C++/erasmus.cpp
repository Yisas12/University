//Jesús Cencerrado Pedrero

#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

long long int erasm(vector <int> b, int n)
{
	long long int cont = 0, suma = 0;

	for (int i = n - 1; i >= 0; --i)
	{
		cont += b[i] * suma;
		suma += b[i];
	}

	return cont;
}

/*void vaciar(vector <int> c, int p)
{
	for (int i = 0; i < p; i++)
	{
		c.pop_back();
	}
}*/

int main()
{
	int casos, estudiantes;
	vector <int> a;

	cin >> casos;
	while (casos != 0)
	{
		long long int funct;

		for (int i = 0; i < casos; i++)
		{
			cin >> estudiantes;
			a.push_back(estudiantes);
		}
		
		funct = erasm(a, casos);
		cout << funct << endl;

		for (int i = 0; i < casos; i++)
		{
			a.pop_back();
		}

		cin >> casos;
	}

	//system("pause");
	return 0;
}