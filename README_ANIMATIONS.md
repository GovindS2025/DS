# 🎯 Sliding Window Algorithm Animations

This repository contains interactive visualizations and animations for understanding the sliding window algorithm, specifically for the "Longest Substring Without Repeating Characters" problem.

## 📁 Files Overview

### 🚀 Interactive Animations
- **`sliding_window_simple_animation.html`** - Simple, reliable animation with step-by-step controls
- **`sliding_window_detailed_animation.html`** - Advanced animation with algorithm step tracking
- **`index.html`** - Landing page with navigation to all animations

### 📝 Documentation & Diagrams
- **`SLIDING_WINDOW_DOCUMENTATION.md`** - Complete documentation with embedded Mermaid diagrams
- **`sliding_window_mermaid_diagram.md`** - Standalone Mermaid diagrams for GitHub
- **`README_ANIMATIONS.md`** - This file

## 🚀 How to View on GitHub

### Method 1: Direct File Viewing
1. **Click on any HTML file** in the GitHub repository
2. **Click "Raw"** to view the raw HTML
3. **Copy the URL** and paste it into a new browser tab
4. **Add `https://htmlpreview.github.io/?`** before the URL

Example:
```
https://htmlpreview.github.io/?https://raw.githubusercontent.com/yourusername/yourrepo/main/sliding_window_simple_animation.html
```

### Method 2: GitHub Pages (Recommended)
1. Go to your repository **Settings**
2. Scroll to **"Pages"** section
3. Select **"Deploy from a branch"**
4. Choose **"main"** branch and **"/ (root)"** folder
5. Click **"Save"**
6. Your animations will be available at:
   ```
   https://yourusername.github.io/yourrepositoryname/
   ```

### Method 3: Mermaid Diagrams
- **Mermaid diagrams render automatically** in GitHub markdown files
- **No setup required** - just view the `.md` files directly
- **Works in pull requests, issues, and README files**

## 🎨 Features

### Interactive Animations
- ✅ **Step-by-step visualization** of the sliding window algorithm
- ✅ **Color-coded elements**: Green (window), Red (left pointer), Blue (right pointer)
- ✅ **Real-time statistics**: Window size, max length, current character
- ✅ **Manual controls**: Start, Next, Previous, Reset buttons
- ✅ **Detailed explanations** for each step

### Mermaid Diagrams
- ✅ **Flowcharts** showing algorithm logic
- ✅ **Step-by-step examples** with visual representations
- ✅ **Complexity analysis** tables
- ✅ **Renders directly in GitHub** without any setup

## 🔧 Local Development

To run the animations locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/yourrepository.git
   cd yourrepository
   ```

2. **Open HTML files** in your browser:
   ```bash
   # On Windows
   start sliding_window_simple_animation.html
   
   # On macOS
   open sliding_window_simple_animation.html
   
   # On Linux
   xdg-open sliding_window_simple_animation.html
   ```

## 📊 Algorithm Examples

### Longest Substring Without Repeating Characters
- **Input**: "abcabcbb"
- **Output**: 3 (substring "abc")
- **Time Complexity**: O(n)
- **Space Complexity**: O(min(m,n))

### Visual Representation
```
String: "abcabcbb"

Step 1: [a]bcabcbb     (window: {a}, maxLength: 1)
Step 2: [ab]cabcbb     (window: {a,b}, maxLength: 2)
Step 3: [abc]abcbb     (window: {a,b,c}, maxLength: 3)
Step 4: a[bc]abcbb     (window: {b,c}, maxLength: 3)
Step 5: a[bca]bcbb     (window: {b,c,a}, maxLength: 3)
Step 6: ab[ca]bcbb     (window: {c,a}, maxLength: 3)
...and so on
```

## 🎯 Usage in Documentation

### For GitHub README
```markdown
## Algorithm Visualization
[![Interactive Animation](https://img.shields.io/badge/View-Interactive%20Animation-blue)](sliding_window_simple_animation.html)
[![Mermaid Diagram](https://img.shields.io/badge/View-Mermaid%20Diagram-green)](sliding_window_mermaid_diagram.md)
```

### For Pull Requests
- Include Mermaid diagrams directly in PR descriptions
- Link to HTML animations for reviewers
- Use static diagrams for code reviews

### For Issues
- Reference specific animation steps when reporting bugs
- Use Mermaid diagrams to explain expected behavior
- Link to interactive examples for complex scenarios

## 🔄 Customization

### Adding New Problems
1. **Copy the HTML template** from existing animations
2. **Update the algorithm data** in the JavaScript section
3. **Modify the visual styling** as needed
4. **Add new Mermaid diagrams** for the new problem

### Styling Changes
- **Colors**: Modify CSS variables in the `<style>` section
- **Animation speed**: Change the `setInterval` timing
- **Layout**: Adjust grid and flexbox properties

## 📚 Learning Resources

- [Sliding Window Technique - GeeksforGeeks](https://www.geeksforgeeks.org/window-sliding-technique/)
- [Two Pointers Technique - LeetCode](https://leetcode.com/explore/interview/card/leetcodes-interview-crash-course-data-structures-and-algorithms/703/arraystrings/4502/)
- [Mermaid Documentation](https://mermaid-js.github.io/mermaid/)

## 🤝 Contributing

1. **Fork the repository**
2. **Create a new branch** for your feature
3. **Add your animations** or improvements
4. **Test thoroughly** in different browsers
5. **Submit a pull request** with clear description

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

**Happy Learning! 🎯** Use these animations to master the sliding window technique and ace your coding interviews!
