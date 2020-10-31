#include <iostream>
#include <vector>
using namespace std;

int resolver(const vector<int> &v)
{
	int cont = 0, i = 0;

	if (v.size() == 0) return 0;

	while (i < v.size() && v[i] == 1) {
		i++;
	}

	if (i <= v.size() - 1) cont = v.size() - i;

	//if (cont > aux) return cont;
	return cont;
}

void resuelveCaso()
{
	vector<int> v;
	int nums, tam, p;

	cin >> tam;

	for (int i = 0; i < tam; i++)
	{
		cin >> nums;
		v.push_back(nums);
	}
	
	p = resolver(v);

	cout << p << "\n";
}

int main()
{
	int casos, x;

	cin >> casos;

	for (int i = 0; i < casos; i++)
	{
		resuelveCaso();
	}

	//system("pause");
	return 0;
}
