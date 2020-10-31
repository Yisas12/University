#include <iostream>
#include <vector>
using namespace std;

bool resolver(const vector<int> &v, int &p, int tamV)
{
	int suma = 0;
	bool y = false;

	for (int i = tamV - 1; i >= 0; i--)
	{
		if (suma == v[i])
		{
			p = i;
			return true;
		}

		suma += v[i];
	}
	/*for (int i = 0; i < tamV - 1; i++)
	{
		if (v[i + 1] == 0 && i == tamV - 2)
		{
			p = i + 1;
			y = true;
		}

		if (v[i] > v[i + 1])
		{
			suma += v[i + 1];
			
			for (int j = i + 1; j < tamV - 1; j++)
			{
				suma += v[j + 1];
			}
			if (suma == v[i])
			{
				p = i;
				y = true;
			}
			suma = 0;
		}
	}*/

	return y;
}

void resuelveCaso()
{
	int tamVector, nums, p = 0;
	bool k = false;
	vector<int> v;

	cin >> tamVector;

	for (int i = 0; i < tamVector; i++)
	{
		cin >> nums;
		v.push_back(nums);
	}

	k = resolver(v, p, tamVector);

	if (k) cout << "Si " << p << "\n";
	else cout << "No\n";
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