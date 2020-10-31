#include <iostream>
#include <vector>
using namespace std;

bool resolver(const vector<long long int> &v, long long int k, long long int ini, long long int fin, bool b, bool p, int contB, int contP)
{
	long long int mitad = (v.size() / 2);
	bool ret = false;
	long long int sum = abs(v[ini] - v[fin]);

	if (v.size() < 4)
	{
		if (sum >= k) return true;
	}
	else
	{
		if (sum >= k)
		{
			ret = true;
			if (fin == mitad - 1) b = true;
			if (ini == mitad) p = true;
		}

		if (!b && contB == 0)
		{
			contB++;
			b = resolver(v, k, ini, mitad - 1, b, p, contB, contP);
		}

		if (!p && contP == 0)
		{
			contP++;
			p = resolver(v, k, mitad, v.size() - 1, b, p, contB, contP);
		}

		if (b && p) ret = true;
		else ret = false;
	}
	
	return ret;
}

bool resuelveCaso()
{
	long long int tam, k, nums;
	vector<long long int> v;
	bool b;

	cin >> tam >> k;
	if (!cin) return false;

	for (int i = 0; i < tam; i++)
	{
		cin >> nums;
		v.push_back(nums);
	}

	if (tam == 1)
	{
		cout << "SI\n";
		return true;
	}
	else b = resolver(v, k, 0, tam - 1, false, false, 0, 0);

	if (b) cout << "SI\n";
	else cout << "NO\n";

	return true;
}

int main()
{
	while (resuelveCaso());

	//system("pause");
	return 0;
}