#include<iostream>
using namespace std;

void resuelveCaso() {
	int n1, n2;
	char slash;

	cin >> n1 >> slash >> n2;

	if (n1 >= n2) cout << "BIEN\n";
	else cout << "MAL\n";
}

int main() {
	int casos;

	cin >> casos;

	for (int i = 0; i < casos; i++) {
		resuelveCaso();
	}

	return 0;
}