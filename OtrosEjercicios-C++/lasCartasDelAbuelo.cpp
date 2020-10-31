#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

/*int main()
{
	char letra;
	int casos, int1, int2, cont = 0, mi, ma;
	vector<int> v;

	cin >> letra;

	while (letra != '\n') {
		v.push_back(letra);
		cin >> letra;
	}

	cin >> casos;

	for (int i = 0; i < casos; i++)
	{
		cin >> int1;
		cin >> int2;

		mi = min(int1, int2);
		ma = max(int1, int2);

		for (mi; mi < ma; mi++)
		{
			if (v[mi] == v[ma]) cont++;
		}
		if (cont == max(int1, int2) - min(int1, int2)) cout << "SI" << endl;
		else cout << "NO" << endl;
	}

	for (int i = 0; i < v.size(); i++)
	{
		v.pop_back();
	}

	system("pause");
	return 0;
}*/

bool resolver() {
	vector <int> v;

	char c, auxchar;
	int numIntervalos, a, b, d;
	int i, j, aux1, aux2, pos;
	bool continuar = true;
	cin.get(c);
	aux1 = 0;
	i = 0;
	j = 1;
	
	while (continuar) {
		cin.get(auxchar);
		if (auxchar != '\n') {
			if (c == auxchar) {
				++j;
			}
			else {
				v.push_back(j + aux1);
				aux1 = v[i];
				j = 1;
				++i;
			}
			c = auxchar;
		}
		else {
			v.push_back(j + aux1);
			continuar = false;
		}
	}

	cin >> numIntervalos;
	if (numIntervalos == 0) {
		return false;
	}
	else {
		for (int k = 0; k < numIntervalos; ++k) {
			cin >> aux1;
			cin >> aux2;
			if (aux1 >= aux2) {
				j = aux1;
				i = aux2;
			}
			else {
				i = aux1;
				j = aux2;
			}

			b = v.size() - 1;
			a = 0;
			pos = -1;
			bool encontrado = false;

			while ((a <= b) && !encontrado) {
				d = (a + b) / 2;
				if (i == v[d]) { encontrado = true; }
				else if (i < v[d]) { b = d - 1; }
				else { a = d + 1; }
			}
			if (encontrado) {
				pos = d + 1;
			}
			else {
				pos = a;
			}

			if (j == i) {
				cout << "SI\n"; // Siempre SI
			}
			else if (j < v[pos]) {
				cout << "SI\n";  // Si está dentro del intervalo de repetición
			}
			else {
				cout << "NO\n";
			}

		}

		cout << "\n";
		cin.ignore(1);
		return true;
	}
}

int main() {

	while (resolver()) {
	}
	return 0;
}