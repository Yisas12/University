#include <iostream>
#include <vector>
using namespace std;

void resolver(const vector<int> &v, int &cantado, int i)
{
	int suma = cantado + i;
	//bool hecho = false;

	if (suma == v[i])
	{
		cout << v[i] << "\n";
	}
	else if(i < v.size() - 1 && suma != v[i]){
		resolver(v, cantado, i + 1);
	}
	else cout << "NO" << "\n";
}

void resuelveCaso()
{
	vector<int> v;
	int numVector, cantado, nums;
	int pos = 0;
	
	cin >> numVector >> cantado;

	for (int i = 0; i < numVector; i++)
	{
		cin >> nums;
		v.push_back(nums);
	}

	resolver(v, cantado, pos);

	for (int i = 0; i < numVector; i++)
	{
		v.pop_back();
	}
}

int main()
{
	int numCasos;

	cin >> numCasos;

	for (int i = 0; i < numCasos; i++)
	{
		resuelveCaso();
	}

	//system("pause");
	return 0;
}