type DogApiResponse = {
    message: string;
    status: string;
};

const img = document.querySelector<HTMLImageElement>('.dog-img');
const loading = document.querySelector<HTMLElement>('.loading');
const errorDiv = document.querySelector<HTMLElement>('.error');

async function showImg(): Promise<void> {
    if (!img || !loading || !errorDiv) {
        return;
    }
    img.classList.remove('visible')
    img.classList.add('hidden');
    loading.classList.add('visible');
    errorDiv.classList.remove('visible');
    errorDiv.classList.add('hidden');

    try {
        const res = await fetch('https://dog.ceo/api/breeds/image/random');
        if (!res.ok) throw new Error('Network response was not ok');
        const data: DogApiResponse = await res.json();
        img.src = data.message;
        img.classList.remove('hidden');
        img.classList.add('visible');
    } catch {
        img.classList.remove('visible');
        errorDiv.classList.remove('hidden');
        errorDiv.classList.add('visible');
        errorDiv.textContent = 'Failed to fetch dog image. Please try again.';
    } finally {
        loading.classList.remove('visible');
        loading.classList.add('hidden');
    }
}