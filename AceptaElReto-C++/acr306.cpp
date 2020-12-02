#include<iostream>
using namespace std;

struct tM {
	long long int izqArr, izqAb, derAr, derAb;
};

tM mult(tM m1, tM m2) {
	tM m3;
	m3.izqArr = ((m1.izqArr*m2.izqArr) % 46337 + (m1.derAr*m2.izqAb) % 46337) % 46337;
	m3.derAr = ((m1.izqArr*m2.derAr) % 46337 + (m1.derAr*m2.derAb) % 46337) % 46337;
	m3.izqAb = ((m1.izqAb*m2.izqArr) % 46337 + (m1.derAb*m2.izqAb) % 46337) % 46337;
	m3.derAb = ((m1.izqAb*m2.derAr) % 46337 + (m1.derAb*m2.derAb) % 46337) % 46337;
	return m3;
}

tM resolver(tM m, long long int n) {
	tM aux;

	if (n == 0) {
		return { 1,0,0,1 };
	}

	if (n % 2 == 0) {
		tM ma = resolver(m, n / 2);
		tM me = mult(ma, ma);
		return me;
	}
	else {
		tM ma = resolver(m, n / 2);
		tM me = mult(m, mult(ma, ma));
		return me;
	}
}

bool resuelveCaso() {
	long long int n;

	cin >> n;

	if (n == 0) return false;

	tM matriz = resolver({ 0, 1, 1, 1 }, n);
	cout << matriz.derAr << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}