#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

void resolver()
{
	vector<int> v;
	int kg, nums, cont = 0, longitud = 0, i = 0, j = 0;
	int numeros;

	cin >> kg;
	cin >> nums;

	while (nums > 0)
	{
		cin >> numeros;
		v.push_back(numeros);

		if (v.at(i) >= kg)
		{
			cont++;
			while (cont > 5)
			{
				if (v.at(j) >= kg) cont--;
				j++;
			}
		}
		if (longitud < (i - j + 1)) longitud = i - j + 1;
		i++;
		nums--;
	}
	cout << longitud << endl;
}

int main() {
	// Para la entrada por fichero.
	//#ifndef DOMJUDGE
	//std::ifstream in("casos.txt");
	//auto cinbuf = std::cin.rdbuf(in.rdbuf());
	//#endif


	unsigned int numCasos;
	cin >> numCasos;
	// Resolvemos
	for (int i = 0; i < numCasos; ++i) {
		resolver();
	}


	//#ifndef DOMJUDGE // para dejar todo como estaba al principio
	//std::cin.rdbuf(cinbuf);
	//system("PAUSE");
	//#endif

	//system("pause");
	return 0;
}