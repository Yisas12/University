#include <iostream>
#include <vector>
using namespace std;

void resolver(const vector<int> &v, const int altura, int &prim, int &seg)
{
	int i = 0, contAux = 0, cont = 0;

	for (i = 0; i < v.size(); i++)
	{
		if (v[i] > altura)
		{
			cont++;
			
			if (cont > contAux)
			{
				seg = i;
				contAux = cont;
				prim = i - cont + 1;
			}
		}
		else cont = 0;

	}

	//cout << prim << " " << seg << "\n";
}

void resuelveCaso()
{
	int tamVector, altura, numeros, prim = 0, seg = 0;
	vector<int> v, r;

	cin >> tamVector >> altura;

	if (tamVector > 0)
	{
		for (int i = 0; i < tamVector; i++)
		{
			cin >> numeros;
			v.push_back(numeros);
		}

		resolver(v, altura, prim, seg);

		cout << prim << " " << seg << "\n";
	}
	
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