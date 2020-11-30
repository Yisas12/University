#include<iostream>
using namespace std;

long long int resolver(long long int x, long long int n) {
	long long int izq, der;

	if (n == 0) return 1;
	else if (n == 1) return x % 31543 ;
	else {
		if (n % 2 == 0) {
			izq = resolver(x, (n / 2));
			izq *= izq;
		}
		else {
			izq = resolver(x, (n / 2));
			der = izq * x;
			izq *= der;
		}
	}
	izq %= 31543;
	return izq;
}

bool resuelveCaso() {
	long long int x, n;

	cin >> x >> n;

	if (n == 0 && x == 0) return false;

	cout << resolver(x, n) << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}