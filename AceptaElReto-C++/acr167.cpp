#include<iostream>
using namespace std;

int resolver(int l) {
	//caso base
	if (l == 1) return 4;

	//caso recursivo
	return 4 * l + 4 * resolver(l / 2);
}

bool resuelveCaso() {
	int l;

	cin >> l;

	if (!cin) return false;

	cout << resolver(l) << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}