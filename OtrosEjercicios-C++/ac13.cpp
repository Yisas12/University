#include <iostream>
#include <vector>
using namespace std;

char resolver(const vector<char> &v, char primera, char segunda)
{
	char letra = ' ';
	int sumAux = 0;
	int i = 0, j = 1;

	if (v[0] != primera) return letra = v[0] - 1;
	if (v[v.size() - 1] != segunda) return letra = v[v.size() - 1] + 1;

	while (i < v.size() - 1 && j < v.size() && letra == ' ')
	{
		if (v[j] - v[i] == 1)
		{
			i++;
			j++;
		}
		else
		{
			letra = v[j] - 1;
		}
	}

	return letra;
}

void resuelveCaso()
{
	char primera, segunda, todas, p;
	vector<char> v;
	//int suma = 0;
	//int i = 97;

	cin >> primera >> segunda;

	//suma = primera + segunda;

	for (int i = primera; i < segunda; i++)
	{
		cin >> todas;
		v.push_back(todas);
	}

	p = resolver(v, primera, segunda);

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