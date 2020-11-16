#include<iostream>
using namespace std;

void resolver(int n) {

	 if(n > 0){
		resolver(n / 6);
		cout << n % 6;
	}
}

void resuelveCaso() {
	int n;
	cin >> n;

	if (n == 0) cout << "0";
	else resolver(n);
	cout << "\n";
}

int main() {
	int casos;

	cin >> casos;

	for (int i = 0; i < casos; i++) resuelveCaso();
	return 0;
}