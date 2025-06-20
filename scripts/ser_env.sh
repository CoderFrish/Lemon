prop() {
  grep "^[[:space:]]*${1}" gradle.properties | cut -d'=' -f2 | sed 's/^[[:space:]]*//; s/\r//'
}

# Set environment variable
echo "commit_tag=$(git log --pretty='%h' -1)" >> $GITHUB_ENV
echo "commit_msg=$(git log --pretty='> [%h] %s' -1)" >> $GITHUB_ENV
echo "branch_name=$(git branch --show-current)" >> $GITHUB_ENV
echo "mcversion=$(prop mcVersion)" >> $GITHUB_ENV
