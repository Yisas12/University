#include <iostream>
#include <vector>
using namespace std;

void imprimirPaginas()
{
	vector<int> v;
	int paginas, m = 0, k = 0;
	bool b = false;
	bool aux = false;
	
	while (cin >> paginas && paginas != 0)
	{
		v.push_back(paginas);
	}

	for (int i = 0; i < v.size() - 1; i++)
	{
		if (v[i] == v[i + 1] - 1)
		{
			m = i;
			b = true;
		}
		else if (v[i] != v[i + 1] - 1)
		{
			k = i;
			aux = true;
		}
	}

	if (b && aux) cout << v[0] << "-" << v[k] << "," << v[k + 1] << "-" << v[m + 1];
	else if(b && !aux) cout << v[0] << "-" << v[m + 1] << endl;
	else if(aux && !b) cout << v[k] << "," << v[k + 1] << endl;
	
}

int main()
{
	imprimirPaginas();

	system("pause");
	return 0;
}