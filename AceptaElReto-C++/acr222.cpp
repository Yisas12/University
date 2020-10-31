#include<iostream>
using namespace std;

int resolver(int x, int y) {
	int res = 1;
	int aux = 1;
	int i = 0;

	while (i < y) {
		aux = (aux*x) % 1000007;
		res = (res + aux) % 1000007;
		i++;
	}

	return res;
}

bool resuelveCaso() {
	int x;
	int y;

	cin >> x >> y;

	if (!cin) return false;

	int p = resolver(x, y);

	cout << p << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}