 function validateForm() {
            var categorySelect = document.getElementById('category');
            var genderSelect = document.getElementById('gender');
            var usernameInput = document.getElementById('username');

            // Sprawdź, czy wybrano kategorię
            if (categorySelect.value === 'Kategorie') {
                alert('Proszę wybrać kategorię.');
                return false;
            }

            // Sprawdź, czy wybrano płeć
            if (genderSelect.value === 'default') {
                alert('Proszę wybrać płeć.');
                return false;
            }

            // Sprawdź, czy pole username nie jest puste
            if (usernameInput.value.trim() === '') {
                alert('Proszę wprowadzić nazwę użytkownika.');
                return false;
            }

            return true;
        }