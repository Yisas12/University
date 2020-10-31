#include<iostream>
#include<vector>
using namespace std;

long long int parejas(const vector<int> &v, const long long int n) {
	long long int res = 0;
	long long int x = 0;

	/*for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			res += v[i] * v[j];
		}
	}*/

	/*for (long long int i = 0; i < n - 1; i++) {
		res += v[i] * v[i + 1];

		if (n > 2 && v[i + 1] > 1) {
			res += v[i + 1];
		}
	}*/

	/*for (long long int i = 0; i < n - 1; i++) {
		if (n > 2) {
			aux = res * v[i] * v[i + 1];
			res = aux;
			//res += res * v[i] * v[i + 1];
		}
		else {
			res = v[i] * v[i + 1];
		}
	}*/

	/*for (long long int i = 1; i < n; i++) {
		res *= v[i];
		x += res;
		//aux += v[i];
	}*/

	for (int i = n - 1; i >= 0; i--) {
		res += v[i] * x;
		x += v[i];
	}
	return res;
}

bool resuelveCaso() {
	long long int paises;
	long long int personas;
	long long int suma = 0;
	vector<int> v;

	cin >> paises;

	if (paises == 0) return false;

	for (int i = 0; i < paises; i++) {
		cin >> personas;
		v.push_back(personas);
		suma += personas;
	}

	long long int p = parejas(v, paises);

	cout << p << "\n";
	return true;
}

int main() {
	while(resuelveCaso());
	return 0;
}