function showImg() {
    const img = document.getElementById('dog-img');
    const loading = document.getElementById('loading');
    const errorDiv = document.getElementById('error');

    if (!img || !loading || !errorDiv) return;

    img.style.display = 'none';
    errorDiv.textContent = '';
    loading.style.display = 'block';
    fetch('https://dog.ceo/api/breeds/image/random')
        .then(res => {
            if (!res.ok) throw new Error('Network response was not ok');
            return res.json();
        })
        .then(data => {
            img.src = data.message;
            img.style.display = 'block';
        })
        .catch(() => {
            errorDiv.textContent = 'Failed to fetch dog image. Please try again.';
        })
        .finally(() => {
            loading.style.display = 'none';
        });
}
