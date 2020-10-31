#include<iostream>
#include<vector>
using namespace std;
int cuantasAbadias(const vector<int> &v, const int &numAbadias) {
	int cont = 1; //la ultima siempre se va a contar, por eso lo inicializo a 1
	int max = v[numAbadias - 1];

	for (int i = numAbadias - 2; i >= 0; i--) {
		if (v[i] > max) {
			cont++;
			max = v[i];
		}
	}

	return cont;
}

bool resuelveCaso() {
	int numAbadias, abadia;
	int llevo = 0, cont = 0;
	vector<int> abadias;

	cin >> numAbadias;

	if (numAbadias == 0) return false;

	for (int i = 0; i < numAbadias; i++) {
		cin >> abadia;
		abadias.push_back(abadia);
	}

	int p = cuantasAbadias(abadias, numAbadias);

	cout << p << "\n";
	return true;
}

int main() {
	while(resuelveCaso());
	return 0;
}