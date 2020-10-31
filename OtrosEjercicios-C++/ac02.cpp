#include <iostream>
#include <vector>
using namespace std;
//Precondicion: {numVector > 0 && 0 <= p < v.length}
//Postcondicion: {forall u, w :: 0 <= u <= p < w < v.length ==> v[u] < v[w]

/*bool resolver()
{
	vector <int> v;
	int p, numVector, num, u = 0;
	bool b = false;

	cin >> numVector;
	cin >> p;

	int w = p + 1;

	for (int i = 0; i < numVector; i++)
	{
		cin >> num;
		v.push_back(num);
	}

	if (p == numVector - 1) {
		cout << "SI" << endl;
		return true;
	}

	while (u <= p && w > p && w < numVector)
	{
		if (v[u] < v[w])
		{
			b = true;
			w++;

			if (w == p - 1)
			{
				u++;
			}
		}
		else
		{
			b = false;
			w = p;
		}
	}

	if (b) cout << "SI" << endl;
	else cout << "NO" << endl;

	for (int i = 0; i < numVector; i++)
	{
		v.pop_back();
	}

	return b;
}*/

/*El coste de este algoritmo es lineal ("n"), ya que tiene varios bucles
que son de orden "n", es decir, que recorren el array "v" 1 vez cada vez
que se ejecuta el programa*/

//Cota: numVector - 1

//I = {0 <= u <= p < w < v.length ^ v[u] < v[w]}

bool resolver(const vector<int> &v, int numero)
{
	int max = v[0];
	bool ok = false;
	int i = 0;

	for (i = 1; i <= numero; i++)
	{
		if (max < v[i]) max = v[i];
	}

	if (i == v.size()) ok = true;

	for (i = numero + 1; i < v.size(); i++)
	{
		if (max < v[i]) ok = true;
		else return false;
	}

	return ok;
}

void resuelveCaso()
{
	int tamVector, numero, numeros;
	vector<int> v;
	bool b;

	cin >> tamVector >> numero;

	for (int i = 0; i < tamVector; i++)
	{
		cin >> numeros;
		v.push_back(numeros);
	}

	b = resolver(v, numero);

	if (b) cout << "SI\n";
	else cout << "NO\n";
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