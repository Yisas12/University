#include <iostream>
#include <vector>
using namespace std;

bool esValida(const vector<int> &min_satis, const int resul, const int local, const int k, const int casos)
{
	if (k == casos - 1) return true;
	if ((local + min_satis[k + 1] >= resul)) return false;
	return true;
}

void funcionarios(const vector<vector<int>> &matriz, const vector<int> &min_satis, vector<bool> &marcas, int &resul, int casos, int parc_satis, int k)
{
	int local;

	for (int i = 0; i < casos; i++)
	{
		if (!marcas[i])
		{
			local = matriz[k][i] + parc_satis; //adquiero el juguete nuevo
			if (esValida(min_satis, resul, local, k, casos))
			{
				marcas[i] = true;

				if (k == casos - 1) //si estoy en el último piso, esSolucion()
				{
					if (resul > local) //vemos si es solución y si lo e la actualizamos
					{
						resul = local;
					}
				}
				else
				{
					funcionarios(matriz, min_satis, marcas, resul, casos, local, k + 1);
				}

				marcas[i] = false;
			}
		}
	}
}

bool resuelveCaso()
{
	int casos, resul = 0;
	vector<vector<int>> matriz;
	vector<int> aux;
	vector<bool> marcas;
	vector<int> min_satis;

	cin >> casos;

	if (casos == 0) return false;

	min_satis.resize(casos);

	//cin >> aux[i];
	for (int i = 0; i < casos; i++)
	{
		aux.resize(casos);

		for (int j = 0; j < casos; j++)
		{
			cin >> aux[j];

			if (aux[j] < min_satis[i])
			{
				min_satis[i] = aux[j];
			}
		}

		matriz.push_back(aux);
	}

	marcas.resize(casos);

	//vector acumulado
	for (int i = casos - 1; i > 0; i--)
	{
		min_satis[i - 1] += min_satis[i];
	}

	//solución inicial
	for (int i = 0; i < casos; i++)
	{
		resul += matriz[i][i];
	}

	funcionarios(matriz, min_satis, marcas, resul, casos, 0, 0);
	cout << resul << "\n";
	return true;

}

int main()
{
	while (resuelveCaso());

	return 0;
}