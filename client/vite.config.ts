import react from '@vitejs/plugin-react';
import { defineConfig } from 'vite';
import checker from 'vite-plugin-checker';
import eslint from 'vite-plugin-eslint';

// eslint-disable-next-line import/no-default-export -- this is the recommended way in https://vitejs.dev/config/
export default defineConfig({
	plugins: [
		react(),
		eslint(),
		checker({
			typescript: true,
		}),
	],
	build: {
		manifest: true,
		assetsDir: '',
	},
	base: '/assets',
	server: {
		origin: 'http://localhost:9000',
	},
});
