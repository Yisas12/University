#include<iostream>
using namespace std;

bool pol(long long int n, int &cont, bool &enc) {
	
	//caso base
	if (n / 10 == 0) return true;
	else {//caso recursivo
		pol(n / 10, cont, enc);

		if (n % cont != 0 && enc) {
			enc = false;
			return false;
		}
		else {
			if (enc) {
				cont++;
				return true;
			}
			else return false;
		}
	}
}

bool resuelveCaso() {
	long long int num;
	bool encontrado = true;

	cin >> num;

	if (!cin) return false;

	int cont = 2;
	bool p = pol(num, cont, encontrado);

	if (p) cout << "POLIDIVISIBLE\n";
	else cout << "NO POLIDIVISIBLE\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}