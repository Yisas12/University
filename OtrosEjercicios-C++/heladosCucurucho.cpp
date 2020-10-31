#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

void helado(int k, int j)
{
	vector<char> v;
		//permutar las posiciones guardandolas en un array y mostrar todas las posibles combinaciones con un for
		for (int i = 0; i < k; i++)
		{
			v.push_back('C');
		}

		for (int i = 0; i < j; i++)
		{
			v.push_back('V');
		}


		for (int i = 0; i < v.size(); i++)
		{
			cout << v.at(i);
		}

		while (next_permutation(v.begin(), v.end())) {
			cout << " ";
			for (int i = 0; i < v.size(); i++)
			{
				cout << v.at(i);
			}
		}
}

int main()
{
	int casos, c, v, j, k;

	cin >> casos;

	for (int i = 0; i < casos; i++)
	{
		cin >> c;
		cin >> v;

		helado(c, v);
		cout << endl;
	}

	//system("pause");
	return 0;
}