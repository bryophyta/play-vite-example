import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import checker from 'vite-plugin-checker';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react(), checker({
    typescript: true,
  })],
  build: {
    manifest: true,
    rollupOptions: {
      input: '/src/main.js'
    }
  },
  server: {
    origin: 'http://localhost:5173',
  }
})
