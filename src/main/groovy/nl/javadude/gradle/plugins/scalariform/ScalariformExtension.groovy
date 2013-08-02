package nl.javadude.gradle.plugins.scalariform

import scalariform.formatter.preferences.*

class ScalariformExtension {
  def prefs = FormattingPreferences$.newInstance().apply()

  def alignParameters(boolean v) {
    prefs = prefs.setPreference(AlignParameters$.newInstance(), v)
  }

  def alignSingleLineCaseStatements(boolean v) {
    prefs = prefs.setPreference(AlignSingleLineCaseStatements$.newInstance(), v)
  }

  def alignSingleLineCaseStatements_maxArrowIndent(int i) {
    prefs = prefs.setPreference(AlignSingleLineCaseStatements.MaxArrowIndent$.newInstance(), i)
  }

  def compactControlReadability(boolean v) {
    prefs = prefs.setPreference(CompactControlReadability$.newInstance(), v)
  }

  def compactStringConcatenation(boolean v) {
    prefs = prefs.setPreference(CompactStringConcatenation$.newInstance(), v)
  }

  def doubleIndentClassDeclaration(boolean v) {
    prefs = prefs.setPreference(DoubleIndentClassDeclaration$.newInstance(), v)
  }

  def formatXml(boolean v) {
    prefs = prefs.setPreference(FormatXml$.newInstance(), v)
  }

  def indentLocalDefs(boolean v) {
    prefs = prefs.setPreference(IndentLocalDefs$.newInstance(), v)
  }

  def indentPackageBlocks(boolean v) {
    prefs = prefs.setPreference(IndentPackageBlocks$.newInstance(), v)
  }

  def indentSpaces(int i) {
    prefs = prefs.setPreference(IndentSpaces$.newInstance(), i)
  }

  def indentWithTabs(boolean v) {
    prefs = prefs.setPreference(IndentWithTabs$.newInstance(), v)
  }

  def multilineScaladocCommentsStartOnFirstLine(boolean v) {
    prefs = prefs.setPreference(MultilineScaladocCommentsStartOnFirstLine$.newInstance(), v)
  }

  def preserveDanglingCloseParenthesis(boolean v) {
    prefs = prefs.setPreference(PreserveDanglingCloseParenthesis$.newInstance(), v)
  }

  def placeScaladocAsterisksBeneathSecondAsterisk(boolean v) {
    prefs = prefs.setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk$.newInstance(), v)
  }

  def preserveSpaceBeforeArguments(boolean v) {
    prefs = prefs.setPreference(PreserveSpaceBeforeArguments$.newInstance(), v)
  }

  def rewriteArrowSymbols(boolean v) {
    prefs = prefs.setPreference(RewriteArrowSymbols$.newInstance(), v)
  }

  def spaceBeforeColon(boolean v) {
    prefs = prefs.setPreference(SpaceBeforeColon$.newInstance(), v)
  }

  def spaceInsideBrackets(boolean v) {
    prefs = prefs.setPreference(SpaceInsideBrackets$.newInstance(), v)
  }

  def spaceInsideParentheses(boolean v) {
    prefs = prefs.setPreference(SpaceInsideParentheses$.newInstance(), v)
  }

  def spacesWithinPatternBinders(boolean v) {
    prefs = prefs.setPreference(SpacesWithinPatternBinders$.newInstance(), v)
  }
}
