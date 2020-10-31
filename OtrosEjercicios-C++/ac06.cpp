//Paula Piñuela Monjas(Paula)
//Camila Pérez Alaniz(Camila96)
//Jesús Cencerrado Pedrero(Yisas)
#include <iostream>
#include <vector>
using namespace std;

int quitarPares(vector <int> &v)
{
	int inicio = 0;

	for (int i = 0; i < v.size(); i++)
	{
		if (v.at(i) % 2 == 0)
		{
			v.at(inicio) = v.at(i);
			inicio++;
		}
	}
	return inicio;
}

bool resolver()
{
	int cant, nums;
	vector<int> v;

	cin >> cant;

	if (cant == -1) return false;

	for (int i = 0; i < cant; i++)
	{
		cin >> nums;
		v.push_back(nums);
	}

	int p = quitarPares(v);

	for (int i = 0; i < p; i++)
	{
		cout << v.at(i) << " ";
	}
	cout << endl;
	return true;
}

int main()
{
	while (resolver());

	system("pause");
	return 0;
}