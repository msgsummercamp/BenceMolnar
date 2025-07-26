type DogApiResponse = {
    message: string;
    status: string;
};

function showImg(): void {
    const img = document.getElementById('dog-img') as HTMLImageElement;
    const loading = document.getElementById('loading') as HTMLElement;
    const errorDiv = document.getElementById('error') as HTMLElement;

    if (!img || !loading || !errorDiv) return;

    img.style.display = 'none';
    errorDiv.textContent = '';
    loading.style.display = 'block';

    fetch('https://dog.ceo/api/breeds/image/random')
        .then((res: Response) => {
            if (!res.ok) throw new Error('Network response was not ok');
            return res.json();
        })
        .then((data: DogApiResponse) => {
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