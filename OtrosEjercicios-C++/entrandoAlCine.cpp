#include <iostream>
using namespace std;
const int TAM = 10000;

void resolver()
{
	unsigned int personas, cont = 0, j;
	int v[TAM];

	cin >> personas;

	for (j = 0; j < personas; j++)
	{
		cin >> v[j];
	}

	for (j = 0; j < personas && v[j] % 2 == 0; j++)
	{
		cont++;
	}

	for (j; j < personas && v[j] % 2 == 1; j++);

	if (j == personas) cout << "SI" << " " << cont << endl;
	else cout << "NO" << endl;
}

int main()
{
	int casos;

	cin >> casos;

	for (int i = 0; i < casos; i++)
	{
		resolver();
	}
	
	//system("pause");
	return 0;
}