#include <iostream>
#include <vector>
using namespace std;

void mezcla(vector<int> &v, int a, int m, int b)
{
	int *u = new int[b - a + 1];
	int i, j, k;

	for (k = a; k <= b; k++)
	{
		u[k - a] = v[k];
	}
	i = 0;
	j = m - a + 1;
	k = a;

	while ((i <= m - a) && (j <= b - a)) {
		if (u[i] <= u[j]) {
			v[k] = u[i];
			i++;
		}
		else {
			v[k] = u[j];
			j++;
		}
		k++;
	}

	while (i <= m - a) {
		v[k] = u[i];
		i++;;
		k++;
	}
	while (j <= b - a) {
		v[k] = u[j];
		j++;
		k++;
	}
	delete[] u;
}

void resolver(vector<int> &v, int a, int b)
{
	int m;

	if (a < b) {
		m = (a + b) / 2;
		resolver(v, a, m);
		resolver(v, m + 1, b);
		mezcla(v, a, m, b);
	}
}

bool resuelveCaso()
{
	int num, componentes, a = 0;
	vector<int> v;

	cin >> num;

	if (!cin) return false;
	else {
		for (int i = 0; i < num; i++)
		{
			cin >> componentes;
			v.push_back(componentes);
		}
		resolver(v, a, v.size() - 1);
		cout << v[0] << "\n";
	}
	return true;
}

int main()
{
	while (resuelveCaso());
	
	//system("pause");
	return 0;
}